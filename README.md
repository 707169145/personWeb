# personWeb
个人网站项目
第一部分:功能介绍

1 个人简历,可以由管理员上传多个pdf附件,选择想到展示的附件进行展示

2 网站介绍,这个部分为简单的html页面

3 文章分享,管理员拥有上传,新增,置顶文章的权限,普通游客为查看

4 留言,游客仅能看到自己或则公开的留言,管理员能看全部,同时拥有管理权限

5 前后端服务器部署于linux系统上

第二部分:前端涉及技术

1 网站页面使用 html5 + css3 + jq , 部署于NGINX服务器

2 Bootstrap框架以及其他jq插件优化界面效果，如sweetAlert插件等

3 页面背景动画采用canvas制作

4 与后端的交互均采用AJAX技术去进行数据的请求操作

第三部分:后端涉及技术

1 基于JAVA语言搭建的后端服务，数据库采用MYSQL，服务器采用TOMCAT7.0

2 采用SSM框架搭建

3 跨域支持，网站采用前后端分离的方式部署于不同的服务器，基本通过AJAX请求交互

4 权限控制，TOKEN验证，一套基于token的验证机制，来识别不同的用户

5 文件的上传下载，主要为简历的管理包括上传下载设置需要展示的简历

6 统一的自定义数据请求返回格式，resultModal

7 redis缓存的使用,主要为文章列表以及留言列表使用
