pipeline
{
    agent any
    tools{
        maven 'maven'
    }
    stages
    {
        stage('Build')
        {
            steps
            {
                dir('build') {
                    git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                    sh "mvn -Dmaven.test.failure.ignore=true clean package"
                }
            }
            post
            {
                success
                {
                    junit '**/build/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'build/target/*.jar'
                }
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
        stage('Regression Automation Tests') {
            steps {
                dir('regression') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git branch: 'main', url: 'https://github.com/Manish12588/2026POMSeries.git'
                        sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml -Denv=qa"
                    }
                }
            }
        }
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'regression/allure-results']]
                    ])
                }
            }
        }
        stage('Publish ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'regression/target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML Regression ChainTest Report',
                                  reportTitles: ''])
            }
        }
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        stage('Sanity Automation Test') {
            steps {
                dir('sanity') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git 'https://github.com/Manish12588/2026POMSeries.git'
                        sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml -Denv=stage"
                    }
                }
            }
        }
        stage('Publish sanity ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'sanity/target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML Sanity ChainTest Report',
                                  reportTitles: ''])
            }
        }
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}