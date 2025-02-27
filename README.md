# 项目介绍
* 本项目是一个简易的航空公司订票系统。
主要由航空系统登录(包含7天自动登录功能)、用户修改密码功能、用户管理页自动查询功能（含分页查询）、航班计划管理功能（含查询航班城市，航班计划状态修改）、机票统计功能、用户新增功能等部分组成。
* 此项目没有使用框架，采用servlet和JDBC进行开发，数据库为mysql
* 开发工具：eclipse+VSCode+Navicat<br>
开发环境：jdk1.8+mysql5+tomcat8.5
![image](https://github.com/user-attachments/assets/c8cf4f00-0940-4a00-892c-b490e83b9770)
# 功能模块介绍
## 1、系统登录
本系统的登录用户包括2种类型，分别是员工和管理员。成功登录后，将根据用户的角色实现不同的功能。员工账户可以实现密码修改与机票统计，管理员账户可以实现航班计划详情与用户管理功能。<br>
当邮箱和密码输入正确时，可以成功登录到系统中；邮箱输入错误时，不能登录且提示“邮箱不存在”；密码输入错误时，不能登录且提示——“密码错误”。勾选“Auto login in 7 days”，应用程序能够在7天内记住最近一次登录成功的Email和Password，7天之内在该计算机运行程序可自动登录进系统。登录后单击Logout退出登录。
![image](https://github.com/user-attachments/assets/e043dee4-330d-47fe-bb21-86d111c28911)
## 2、用户密码修改
单击用户界面中的 “Modify Password”菜单项时，将会打开此界面。<br>
此界面允许当前员工用户修改密码，Email 和 Name 自动加载但不可修改。要求新密码必须是 8-16 位字符，两次密码需要输入一致。密码输入完毕后，单击“Submit”按钮重新跳转到登录界面。单击“Cancel”，清除当前已填写密码信息。
![image](https://github.com/user-attachments/assets/02b4a7df-40c4-490d-97fb-96f643e47b79)
## 3、机票统计
单击用户界面中的 “Ticket Statistics”菜单项时，将会打开此界面。<br>
员工能够根据需要统计某时间段内机票的销售情况，仅统计状态为“Confirmed”的航班信息，统计结果以表格形式显示，且根据月份升序显示。
![image](https://github.com/user-attachments/assets/eefe3bd8-7c7c-408f-a495-7a0c9759ead7)
## 4、航班计划管理
单击管理员界面中的 “Flight Schedule Management”菜单项时，将会打开此界面。<br>
当航班“Confirmed”状态时，通过右下方的按钮可以取消航班；当航班“Canceled”状态时，通过右下方的按钮可以确认航班。
## 5、用户查询
单击管理员界面中的 “User Management”菜单项时，将会打开此界面。
