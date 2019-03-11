node('SLAVE') {
    stage('Clone') {
        git 'https://github.com/sivaganesan23/studentproj-code.git'
    }
    stage('Compile') {
        sh 'mvn compile -D VERSION=$VERSION -D TYPE=$VERSIONTYPE '
    }
    stage('Quality Check') {
        sh 'mvn sonar:sonar -Dsonar.projectKey=sivaganesan23_studentproj-code -Dsonar.organization=sivaganesan23-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=21e8fdbec9796ec78e5ddcfeb2c1cd0659289e5b -D VERSION=$VERSION -D TYPE=$VERSIONTYPE'
    }    
    stage('Package') {
        sh 'mvn package -D VERSION=$VERSION -D TYPE=$VERSIONTYPE'
    }
    stage('Upload Artifacts') {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'NEXUS-USER-PASS', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        sh 'mvn deploy -D NEXUS_USERNAME=$USERNAME -D NEXUS_PASSWORD=$PASSWORD -D VERSION=$VERSION -D TYPE=$VERSIONTYPE'
    }  
    }     
}
