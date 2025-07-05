# Install dependencies
package { 'openjdk-11-jdk':
  ensure => installed,
}

package { 'maven':
  ensure => installed,
}

package { 'git':
  ensure => installed,
}

# Clone the repository
exec { 'clone_coreproject_repo':
  command => 'git clone https://github.com/VishalShekha/devops.git /opt/devops',
  creates => '/opt/devops',  
  path    => ['/usr/bin', '/usr/local/bin'],
  cwd     => '/opt',
}

# Ensure directories exist
file { '/opt/devops/logs':
  ensure => directory,
  owner  => 'root',
  group  => 'root',
  mode   => '0755',
}

file { '/opt/devops/deployed':
  ensure => directory,
  owner  => 'root',
  group  => 'root',
  mode   => '0755',
}

# Build the project with Maven
exec { 'maven_build':
  command => 'mvn clean install',
  cwd     => '/opt/devops',
  path    => ['/usr/bin', '/usr/local/bin'],
  unless  => 'test -f /opt/devops/target/coreproject-0.0.1-SNAPSHOT.jar',
}

# Deploy the JAR file
file { '/opt/devops/deployed/coreproject.jar':
  ensure  => 'present',
  source  => '/opt/devops/target/coreproject-0.0.1-SNAPSHOT.jar',
  require => Exec['maven_build'],
}

# Start the Spring Boot application
exec { 'start_spring_boot':
  command => 'java -jar /opt/devops/deployed/coreproject.jar > /opt/devops/logs/application.log 2>&1 &',
  cwd     => '/opt/devops',
  path    => ['/usr/bin', '/usr/local/bin'],
  require => File['/opt/devops/deployed/coreproject.jar'],
  creates => '/opt/devops/logs/application.log',
  unless  => 'test -f /opt/devops/logs/application.log',
}

# Create systemd service
file { '/etc/systemd/system/coreproject.service':
  ensure  => file,
  content => '
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

[Install]
WantedBy=multi-user.target
',
  mode    => '0644',
}

# Manage the service
service { 'coreproject':
  ensure     => running,
  enable     => true,
  require    => [File['/etc/systemd/system/coreproject.service'], File['/opt/devops/deployed/coreproject.jar']],
  subscribe  => File['/etc/systemd/system/coreproject.service'],
}
