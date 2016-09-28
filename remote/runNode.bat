set HUB_HOSTNAME=EPRURYAW0080
set GRID=selenium-server-standalone-3.0.0.jar
set HUB_URL=http://%HUB_HOSTNAME%:4444/grid/register
set BR_IE="browserName=interner explorer, maxInstances=3, platform=WINDOWS"
set BR_FF="browserName=firefox, version=45.0, maxInstances=3, platform=WINDOWS"
set BR_CHROME="browserName=chrome, maxInstances=3, platform=WINDOWS"
java -jar %GRID% -role node -hub %HUB_URL% -browser %BR_FF% -browser %BR_CHROME% -timeout 900000 -port 5555