
pipeline {
        agent {
            node {label 'host'}
        }

	  triggers {
                    pollSCM('* * * * *')
                    }
        
          stages {

					 stage("Test login service") {
                        steps { 
                            sh "./gradlew login-service:test"
                          }
                        steps { 
                            sh "./gradlew login-service:intTest"
                          }
                    }

					
                    stage("Build login service") {
                        steps { 
                            sh "./gradlew login-service:build"
                          }
                        }
                                                          
                                      
                                      
                                      
                   }  
           post { 
		        always { 
		             sh "docker compose up -d"
		        }
    }                 
}
