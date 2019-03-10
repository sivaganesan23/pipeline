node('SLAVE') {
    stage('Clone') {
        git 'https://github.com/sivaganesan23/studentproj-code.git'
    }
    stage('Compile') {
        sh 'mvn compile'
    }
    stage('Quality Check') {
        sh 'mvn sonar:sonar -Dsonar.projectKey=sivaganesan23_studentproj-code -Dsonar.organization=sivaganesan23-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=21e8fdbec9796ec78e5ddcfeb2c1cd0659289e5b'
    }    
}
