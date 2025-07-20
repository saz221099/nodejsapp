job('Aplicacion Node.js DSL') {
    description('Aplicacin Node JS DSL para el curso de Jenkins')
    scm {
        git('https://github.com/saz221099/nodejsapp.git', 'master') { node ->
            node / gitConfigName('saz221099')
            node / gitConfigEmail('carlosjosebonilla57@gmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('saz221099/nodejsapp')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('5c952c03-0213-4332-9904-f1b69cb96346')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
