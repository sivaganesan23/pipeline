  displayName('seed-pipe_converted')

  keepDependencies(false)

  quietPeriod(0)

  checkoutRetryCount(0)
  configure { flowdefinition ->

    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      
      'triggers' {
        
        'hudson.triggers.SCMTrigger' {
          
          'spec'('H/2 * * * *')

          'ignorePostCommitHooks'(false)

        }

      }

    }

      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git@3.9.3') {

        'configVersion'(2)

        'userRemoteConfigs' {

          'hudson.plugins.git.UserRemoteConfig' {

pipelineJob('seed-pipe_converted') {

  description('')

  displayName('seed-pipe_converted')

  keepDependencies(false)

  quietPeriod(0)

  checkoutRetryCount(0)

  disabled(false)

  concurrentBuild(false)

  configure { flowdefinition ->

    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      
      'triggers' {
        
        'hudson.triggers.SCMTrigger' {
          
          'spec'('H/2 * * * *')

          'ignorePostCommitHooks'(false)

        }

      }

    }

    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps@2.64') {

      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git@3.9.3') {

        'configVersion'(2)

        'userRemoteConfigs' {

          'hudson.plugins.git.UserRemoteConfig' {

pipelineJob('seed-pipe_converted') {

  description('')

  displayName('seed-pipe_converted')

  keepDependencies(false)

  quietPeriod(0)

  checkoutRetryCount(0)

  disabled(false)

  concurrentBuild(false)

  configure { flowdefinition ->

    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      
      'triggers' {
        
        'hudson.triggers.SCMTrigger' {
          
          'spec'('H/2 * * * *')

          'ignorePostCommitHooks'(false)

        }

      }

    }

    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps@2.64') {

      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git@3.9.3') {

        'configVersion'(2)

        'userRemoteConfigs' {

          'hudson.plugins.git.UserRemoteConfig' {

            'url'('https://github.com/sivaganesan23/pipeline.git')

          }

        }

        'branches' {

          'hudson.plugins.git.BranchSpec' {

            'name'('*/master')

          }

        }

        'doGenerateSubmoduleConfigurations'(false)

        'submoduleCfg'(class:'list')

        'extensions'()

      }

      'scriptPath'('pipeline.groovy')

      'lightweight'(true)

    }

  }

}

   
