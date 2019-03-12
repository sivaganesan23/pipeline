pipelineJob('seed-pipe_converted') {
  description('')
  displayName('seed-pipe_converted')
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {     
      'triggers' {        
        'hudson.triggers.SCMTrigger' {          
          'spec'('H/2 * * * *')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
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

pipelineJob('seed-prod-pipe_converted') {
  description('')
  displayName('seed-pipe_converted')
  configure { flowdefinition ->

    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
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
      'scriptPath'('prod-pipeline.groovy')
      'lightweight'(true)
    }
  }
}
