pipeline {
   agent any
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
   }
}
