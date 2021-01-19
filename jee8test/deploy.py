AdminApp.install('/work/app/jee8test.war', '[ -appname jee8test.war -contextroot / -MapWebModToVH [[ jee8test.war jee8test.war,WEB-INF/web.xml default_host]]]')
AdminConfig.save()