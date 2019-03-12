
node('SLAVE') {
    stage('Clone') {
        dir('APPCODE') {
        git 'https://github.com/sivaganesan23/studentproj-code.git'
        }
    }
    stage('Compile') {
        dir('APPCODE') {
        sh 'mvn compile -D VERSION=$VERSION -D TYPE=$VERSIONTYPE '
    }
    }
    stage('Quality Check') {
        dir('APPCODE') {
        sh 'mvn sonar:sonar -Dsonar.projectKey=sivaganesan23_studentproj-code -Dsonar.organization=sivaganesan23-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=21e8fdbec9796ec78e5ddcfeb2c1cd0659289e5b -D VERSION=$VERSION -D TYPE=$VERSIONTYPE'
    }    
    }
    stage('Package') {
        dir('APPCODE') {
        sh 'mvn package -D VERSION=$VERSION -D TYPE=$VERSIONTYPE'
    }
    }
    stage('Test-Env-Creation') {
        withCredentials([file(credentialsId: 'CENTOS-USER-PEM', variable: 'FILE')]) {
            sh '''cat $FILE >/home/centos/devops.pem
            chmod 600 /home/centos/devops.pem
            '''
        try {
        dir('TERRAFORM') {
           git 'https://github.com/sivaganesan23/terraform.git'
           wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'ACCESS-KEY', usernameVariable: 'ACCESS-KEY-ID', passwordVariable: 'SECRET-KEY']]) {
       sh ''' 
       export AWS_ACCESS_KEY_ID="${ACCESS-KEY-ID}"
       export AWS_SECRET_ACCESS_KEY="${SECRET-KEY}"
       export AWS_DEFAULT_REGION="us-east-2"
       cd stack-test-env
       terraform init
       terraform apply -yes
       '''
            }  
          }
         }     
       }
      finally {
           sh 'rm -f /home/centos/devops.pem'
       } 
        }
    }
}
