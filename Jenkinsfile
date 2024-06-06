pipeline {
    agent any
    tools {
        maven 'maven-3'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/Searbelaez1992/Module2UIBookStore.git', branch: "dev"
            }
            
        }
        
        stage('Static Code Analysis') {
            steps {
                sh "mvn pmd:pmd"
                sh "mvn checkstyle:checkstyle"
                sh "mvn spotbugs:spotbugs"
            }
        }
        
        stage('Publish Static Code Analysis Reports') {
            steps {
                recordIssues sourceCodeRetention: 'LAST_BUILD', tools: [checkStyle(pattern: 'reports/checkstyle/checkstyle.xml'), pmdParser(pattern: '**/target/pmd.xml'), spotBugs(pattern: '**/target/spotbugsXml.xml', useRankAsPriority: true)]
            }
        }
        
        
        stage('Test Coverage') {
            steps {
                junit 'target/surefire-reports/**/*.xml'
                jacoco()
            }
        }
        
        stage('Run Unit Test Cases') {
            steps {
                sh "mvn clean test"
            }
            
        }
        stage('Build') {
            steps {
                
                git url: 'https://github.com/Searbelaez1992/Module2UIBookStore.git', branch: "main"

                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
