pipeline{

    agent any

    stages{
            stage("build"){
                steps{
                    script{
                        bat 'mvn compile'
                    }
                        echo 'Building Project'
                }

                }

                stage("test"){
                    steps{
                        script{
                                bat 'mvn install'
                            }
                        echo 'Testing Project'
                    }

                    }

                stage("deploy"){
                    steps{
                        script{
                            bat 'mvn test'
                        }
                         echo 'Deploying Project'
                    }

                        }

    }

}