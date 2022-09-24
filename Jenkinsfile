pipeline { 
    agent any  
    stages { 
        stage('Build') { 
            steps { 
               echo 'Building using mvn'
               sh 'mvn clean package'
            }
        }
    }
}
