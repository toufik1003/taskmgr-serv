pipeline {

  agent any
  tools { 
        maven 'maven3' 
        jdk 'JDK8' 
        //docker 'docker17'
  }

  stages {
      
    stage('Clone Repository') {
         steps {
            // get code from a GitHub repository
            git 'https://github.com/toufik1003/taskmgr-serv.git'
         }
    } 

    stage ('Initialize') {
            steps {
                bat '''
                    echo "PATH = %PATH%"
                ''' 
            }
    }
    
    stage ('Build') {
      steps {
        bat 'mvn clean package'
      }
    }
	
	stage ('SetUp Database') {
      steps {
        bat '''
			@echo off
			dockerx inspect -f '{{.State.Running}}' mysql-db-taskmgr  && (
				echo [mysql-db-taskmgr] exists, do nothing
			) || (
			  echo container [mysql-db-taskmgr] does not exists, need to create and run ...
			  dockerx run -p 2012:3307 --name=mysql-db-taskmgr -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_USER=test -e MYSQL_PASSWORD=test123 -e MYSQL_DATABASE=taskmanager -d mysql
			)            
        '''
      }
	}
	
    stage ('Docker Build Image') {
      steps {
        bat '''
            echo "build docker image ..."
            dockerx build -f Dockerfile -t taskmanager-srv .
        '''
      }
	}
	
	stage ('Docker Deploy') {
      steps {
        bat '''
            @echo off
			dockerx inspect -f '{{.State.Running}}' taskmanager-srv-container  && (
				dockerx container rm -f taskmanager-srv-container
				dockerx run -t -d --name taskmanager-srv-container --link mysql-db-taskmgr:mysql -p 1081:9091 taskmanager-srv
			) || (
			  echo container [taskmanager-srv-container] does not exists, need to create and run ...
			  dockerx run -t -d --name taskmanager-srv-container --link mysql-db-taskmgr:mysql -p 1081:9091 taskmanager-srv
			)
        '''
      }      
    }
    
 }

}