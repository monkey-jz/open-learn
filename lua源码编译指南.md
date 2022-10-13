##背景
由于公司使用的游戏开发语言是lua,一般lua文件都是直接由引擎加载然后去执行函数渲染界面.自己给引擎写的device插件android平台时用到lua与C++绑定所依赖的开源库[lua-Intf](https://github.com/SteveKChiu/lua-intf "lua-intf"),这个库需要lua源码的头文件以及编译好的so文件作为依赖,但是so库和头文件都是由引擎提供的,为了搞清楚C++加载lua文件的流程与绑定过程自己决定写个脱离引擎的demo,这就需要自己去编译lua源码获取动态链接库嵌入android,以下是编译过程。

##流程
一.平台：

ubuntu16.04

二.源码下载及生成静态库：
[https://www.lua.org/download.html](https://www.lua.org/download.html "lua源码下载")
也可以依次执行命令:
获取lua源码
curl -R -O http://www.lua.org/ftp/lua-5.3.5.tar.gz
解压
tax zxf lua-5.3.5tar.gz
进入lua源码文件夹
cd lua-5.3.5
编译
make linux test
如果出现以下错误
![](https://i.imgur.com/Raf72sq.png)
以下文章给出解决方案
[https://stackoverflow.com/questions/23085076/readline-readline-h-file-not-found](https://stackoverflow.com/questions/23085076/readline-readline-h-file-not-found)
本人使用的是sudo apt-get install libreadline-dev 命令解决的
然后再执行一遍 make linux test 即可。

注意此步完成后会在lua-5.3.5/src目录下生成liblua.a库这个是静态库不是我们需要的

三.生成动态链接库

为了少走弯路这里注意需要把上一步解压后的lua-5.3.5文件夹删除，重新解压

tax zxf lua-5.3.5tar.gz

这是因为生成动态链接库需要更改两个Makefile文件,如果不删除lua-5.3.5文件夹即使都改对了依然会报错，所以需要从头开始,先更改后编译。

解压完之后不要执行make linux test这个命令，如果执行了这个命令会导致后面不管怎么改动态库都会生成失败

打开lua-5.3.5目录下的Makefile文件 vim Makefile
在 LUA_A=liblua.a 下面添加一行 LUA_SO=liblua.so即：

![](https://i.imgur.com/19IBTw8.png)

保存退出后进入src目录打开Makefile文件 vim Makefile
修改为如下所示：
![](https://i.imgur.com/lDi0JPq.png)

![](https://i.imgur.com/q36ciZP.png)

![](https://i.imgur.com/wDYSF0D.png)

![](https://i.imgur.com/huGacEH.png)

务必注意大小写及空格

改完保存之后退出到lua-5.3.5目录下执行make linux test即可在src目录下生成liblua.so库(test)可省略,在使用的时候需要把lua_Intf库文件及lua源码的头文件即src目录下的.h文件拷贝到android项目中,并用CmakeLists.txt把so库链接进android程序.












