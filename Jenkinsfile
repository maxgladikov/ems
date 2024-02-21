pipeline {
    
    agent {label 'host'}
        
    
    tools { gradle 'gradle_8.6'
            }

    triggers {
                pollSCM('* * * * *')
            }
    
    stages {

		 stage("Stop all containers") {
            steps { 
                 sh "docker compose down"
              }
            }
					
		 stage("Test admin service") {
            steps { 
                sh "gradle admin-service:test"
				sh "gradle admin-service:intTest"
              }                       
        }
                                      
        stage("Build admin service") {
            steps { 
                sh "gradle admin-service:build"
              }
            }       
           } 
   post { 
        always { 
             sh "docker compose up -d"
        } 
        success {
            echo 'Successfully!'
        } 
        failure {
            echo 'Failed!'
        }
	}                 
}
