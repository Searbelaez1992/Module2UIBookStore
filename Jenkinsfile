pipeline {
    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven 'maven-3'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/Searbelaez1992/Module2UIBookStore.git', branch: "main"
            }
            
        }
        
       
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git url: 'https://github.com/Searbelaez1992/Module2UIBookStore.git', branch: "main"

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                 //bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
