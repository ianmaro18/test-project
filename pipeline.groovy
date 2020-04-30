node {

	try {

		stage('cleanup workspace') {
			cleanWs()
		}

		stage('checkout') {
			//git 'https://github.com/ianmaro18/test-project.git'
			//git branch: 'feature/AddCodeCoverage', url: 'https://github.com/ianmaro18/test-project.git'
			git branch: 'feature/FixFailingTest', url: 'https://github.com/ianmaro18/test-project.git'
		}

		stage('nuget restore') {
			//bat label: '@@@ Restoring NuGet Packages', script: 'dotnet restore'
			bat label: '@@@ Restoring NuGet Packages', script: 'nuget restore'
		}

		stage('build') {
			//bat label: '@@@ Running MS Build', script: 'msbuild /t:restore'
			bat label: '@@@ Running MS Build', script: 'msbuild'
		}

		def test_path = "UnitTests"
		dir(test_path) {
			
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

			stage('run test coverage') {
				//bat label: '', script: 'OpenCover.Console.exe -target:dotnet.exe -filter:"+[Application*]* -[Tests*]*" -output:TestResults\\testcoverage.opencover.xml -register:user -showunvisited -oldStyle -returntargetcode -hideskipped:Filter -targetargs:"test --no-build --logger \"trx;LogFileName=testresults.dotnet.xml\""'
				bat label: '', script: 'OpenCover.Console.exe -target:dotnet.exe -filter:"+[Application*]* -[Tests*]*" -output:TestResults\\testcoverage.opencover.xml -register:user -showunvisited -oldStyle -returntargetcode -hideskipped:Filter -targetargs:"test --no-build"'
			}

			stage('publish test coverage') {
				publishCoverage adapters: [opencoverAdapter(mergeToOneReport: true, path: 'TestResults\\testcoverage.opencover.xml')], sourceFileResolver: sourceFiles('NEVER_STORE')
			}

		}
	}
	catch (err) {
		echo "Caught: ${err}"
		currentBuild.result = 'Failure'
	}

}