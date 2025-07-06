package { ['openjdk-11-jdk', 'maven', 'git']:
  ensure => installed,
}

exec { 'clone_coreproject_repo':
  command => 'git clone https://github.com/VishalShekha/devops.git /opt/devops',
  creates => '/opt/devops',
  path    => ['/usr/bin', '/usr/local/bin'],
  cwd     => '/opt',
}

file { ['/opt/devops/logs', '/opt/devops/deployed']:
  ensure => directory,
  owner  => 'root',
  group  => 'root',
  mode   => '0755',
}

exec { 'maven_build':
  command => 'mvn clean install',
  cwd     => '/opt/devops',
  path    => ['/usr/bin', '/usr/local/bin'],
  unless  => 'test -f /opt/devops/target/coreproject-0.0.1-SNAPSHOT.jar',
  require => Exec['clone_coreproject_repo'],
}

exec { 'copy_jar':
  command => 'cp /opt/devops/target/coreproject-0.0.1-SNAPSHOT.jar /opt/devops/deployed/coreproject.jar',
  creates => '/opt/devops/deployed/coreproject.jar',
  path    => ['/bin', '/usr/bin', '/usr/local/bin'],
  require => Exec['maven_build'],
}

file { '/etc/systemd/system/coreproject.service':
  ensure  => file,
  content => @("EOF"),
    [Unit]
    Description=CoreProject Spring Boot Application
    After=network.target

    [Service]
    ExecStart=/usr/bin/java -jar /opt/devops/deployed/coreproject.jar --server.port=8081
    WorkingDirectory=/opt/devops
    StandardOutput=append:/opt/devops/logs/application.log
    StandardError=append:/opt/devops/logs/application.log
    Restart=always
    User=root
    Group=root
    SuccessExitStatus=143

    [Install]
    WantedBy=multi-user.target
    | EOF
  mode    => '0644',
  require => Exec['copy_jar'],
}

exec { 'daemon_reload':
  command     => '/bin/systemctl daemon-reload',
  refreshonly => true,
  subscribe   => File['/etc/systemd/system/coreproject.service'],
}

service { 'coreproject':
  ensure    => running,
  enable    => true,
  require   => [Exec['daemon_reload'], Exec['copy_jar']],
  subscribe => File['/etc/systemd/system/coreproject.service'],
}
