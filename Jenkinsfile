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
//                 sh "gradle admin-service:test"
				sh "gradle admin-service:intTest"
              }                       
        }

        stage("Test gateway service") {
            steps { 
                sh "gradle gateway-service:test"
				sh "gradle gateway-service:intTest"
              }                       
        }
                                      
        stage("Build admin service") {
            steps { 
                sh "gradle admin-service:build"
              }
            }

         stage("Build discovery service") {
                    steps {
                        sh "gradle discovery-service:build"
                      }
                    }

        stage("Build gateway service") {
            steps { 
                sh "gradle gateway-service:build"
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
