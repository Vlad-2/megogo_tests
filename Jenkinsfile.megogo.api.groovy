pipeline {
    agent any

    parameters {
        string(name: 'browser', defaultValue: 'chrome', description: 'Выберите браузер')
        string(name: 'environment', defaultValue: 'dev', description: 'Выберите окружение')
    }

    tools {
        jdk 'java17'
        maven 'Maven 3.8.7'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Vlad-2/megogo_tests'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Tests') {
            steps {
                sh '''
            mvn -B -U -Dfile=src/test/resources/testng_xml/api.xml -Dbrowser=${params.browser} -Denvironment=${params.environment} clean test
            '''
            }
        }
    }
    post {
        always {
            script {
                // Генерация Allure отчета всегда
                allure includeProperties: false, results: [[path: 'target/allure-results']]
            }
        }
        success {
            echo "Тесты прошли успешно"
        }
        failure {
            echo "Тесты завершились с ошибкой"
        }
    }
}