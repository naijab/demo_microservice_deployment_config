package com.naijab.pipeline

class LineNotify  {

  def send(lineToken, status)  {
    def jobName =  "$JOB_NAME -- $BRANCH_NAME" 
    def buildNo = $BUILD_NUMBER
      
    def url = 'https://notify-api.line.me/api/notify'
    def message = "${jobName}\nBuild #${buildNo} ${status}\r\n"
    sh "curl ${url} -H 'Authorization: Bearer ${lineToken}' -F 'message=${message}'"
  }

}