# ncu_mis_sa_team_2

## 從GitHub上抓專案到Eclipse
https://youtu.be/MkyzjN8zDH0
## 從Eclipse上傳到GitHub
https://youtu.be/4TsdDw03TGY

## QA
### server port 被佔用？
搜尋 命令提示字元>右鍵>以系統管理員身份開啟
輸入
netstat -a -n -o | find 欲搜尋埠號
（如：netstat -a -n -o | find 8090）
>enter
  TCP    0.0.0.0:8090           0.0.0.0:0              LISTENING       29256
>輸入
taskkill /F /pid 29256
