node {

    try {
        
        stage('cleanup workspace') {
            cleanWs()
        }
        
        stage('checkout') {
            //git 'https://github.com/ianmaro18/test-project.git'
            git branch: 'feature/AddCodeCoverage', url: 'https://github.com/ianmaro18/test-project.git'
        }
        
        stage('nuget restore') {
            //bat label: '@@@ Restoring NuGet Packages', script: 'dotnet restore'
            bat label: '@@@ Restoring NuGet Packages', script: 'nuget restore'
        }
            
        stage('build') {
            //bat label: '@@@ Running MS Build', script: 'msbuild /t:restore'
            bat label: '@@@ Running MS Build', script: 'msbuild'
        }
        
        def build_path = "UnitTests"
        dir(build_path) {
            
            try {
                stage('run test') {
                    bat label: '@@@ RestoringRunning Tests', script: 'dotnet test --no-build --logger "trx;LogFileName=testresults.dotnet.xml"'
                }
			}
            catch (err) {
                
            }    
            
            stage('publish test') {
                step([$class: 'XUnitPublisher', testTimeMargin: '3000', thresholdMode: 1, thresholds: [], tools: [MSTest(deleteOutputFiles: true, failIfNotNew: true, pattern: 'TestResults/testresults.dotnet.xml', skipNoTestFiles: false, stopProcessingIfError: true)]])
            }

        }
    } catch (err) {
        echo "Caught: ${err}"
        currentBuild.result = 'Failure'
    }

}