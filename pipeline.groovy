node('SLAVE') {
    stage('Clone') {
        git 'https://github.com/sivaganesan23/studentproj-code.git'
    }
    stage('Compile') {
        sh 'mvn compile'
    }
}
