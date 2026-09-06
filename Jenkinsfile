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
                echo("Simulated dev build stage — in a real org's pipeline, this is where the application under test would be built and packaged before QA automation runs. This project targets a public demo application, so there is nothing of ours to build here.")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("Simulated deploy stage — represents the application under test being deployed to a QA environment before automation runs against it.")
            }
        }
        stage('Regression Automation Tests') {
            steps {
                dir('regression') {
                    script {
                        try {
                            git branch: 'main', url: 'https://github.com/Manish12588/2026POMSeries.git'
                            sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml -Denv=qa"
                        } catch (err) {
                            unstable("Regression tests failed: ${err}")
                        }
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
                echo("Simulated deploy stage — represents the application under test being deployed to a staging environment before the sanity suite runs.")
            }
        }
        stage('Sanity Automation Tests') {
            steps {
                dir('sanity') {
                    script {
                        try {
                            git branch: 'main', url: 'https://github.com/Manish12588/2026POMSeries.git'
                            sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml -Denv=stage"
                        } catch (err) {
                            unstable("Sanity tests failed: ${err}")
                        }
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
                echo("Simulated deploy stage — represents promotion to production after both regression and sanity suites have passed.")
            }
        }
    }
}