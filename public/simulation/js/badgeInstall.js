var flashvars = {};
flashvars.id = 'air_badge';
flashvars.airversion = '3.1';
flashvars.appname = 'MiracleSimulation';
flashvars.appurl = 'http://scihub.uio.no/public/simulation/simuApril2012.air';
flashvars.imageurl = 'assets/badge.png';
flashvars.appid = 'simuApril2012.air';
flashvars.pubid = '';
flashvars.appversion = '1.1.6';
flashvars.installarg = 'null';
flashvars.launcharg = 'null';
flashvars.helpurl = 'help.html';
flashvars.hidehelp = 'true';
flashvars.skiptransition = 'false';
flashvars.titlecolor = '#3fbb2e';
flashvars.buttonlabelcolor = '#FFFFFF';
flashvars.appnamecolor = '#3fbb2e';


var params = {};
params.wmode = 'window';
params.menu = 'false';
params.quality = 'high';

var attributes = {};

swfobject.embedSWF('install_swf/AIRInstallBadge.swf', 'badge_div', '215', '180', '9.0.115', 'install_swf/expressInstall.swf', flashvars, params, attributes);