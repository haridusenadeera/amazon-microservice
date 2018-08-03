pipeline {
   agent any
   environment {
      CFAPI = 'https://api.run.pivotal.io'
      CFUSERNAME = credentials('PCFUSER')
      CFPASS = credentials('PCFPASS')
    }
   stages {
       stage('Build') {
           steps {
               echo 'Building Application: accounts...'
               sh './gradlew applications/account:build'
               echo 'Building Application: orders...'
               sh './gradlew applications/order:build'
               echo 'Building Application: products...'
               sh './gradlew applications/product:build'
               echo 'Building Application: shipments...'
               sh './gradlew applications/shipment:build'
           }
       }
       stage('Deploy') {
            steps {
                echo 'Logging in to CF...'
                sh 'cf login -a $CFAPI -u $CFUSERNAME -p $CFPASS -o solstice-org -s hsenadeera-cnt'
                echo 'Deploying....'
                sh 'cf push account -t 180 --random-route -p applications/account/build/libs/applications/account-0.0.1-SNAPSHOT.jar'
                sh 'cf push order -t 180 --random-route -p applications/order/build/libs/applications/order-0.0.1-SNAPSHOT.jar'
                sh 'cf push product -t 180 --random-route -p applications/product/build/libs/applications/product-0.0.1-SNAPSHOT.jar'
                sh 'cf push shipment -t 180 --random-route -p applications/shipment/build/libs/applications/shipment-0.0.1-SNAPSHOT.jar'
            }
        }
   }
}
