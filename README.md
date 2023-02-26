### swift-send

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Apache License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/riverify/swift-send">
    <img src="https://avatars.githubusercontent.com/u/97610640?v=4" alt="Logo" width="80" height="80">
  </a>

<h2 align="center">Swift Send</h2>

  <p align="center">
    <h3>丝滑妙送 简洁好用的文件中转站</h3>
    <br />
    <a href="https://github.com/riverify/swift-send">View Code</a>
    ·
    <a href="https://github.com/riverify/swift-send/issues">Report Bug</a>
    ·
    <a href="https://github.com/riverify/swift-send/issues">Request Feature</a>
  </p>
</div>



<!-- 内容目录 -->
<details open="open">
  <summary>内容目录</summary>
  <ol>
    <li>
      <a href="#关于本项目">关于本项目</a>
      <ul>
        <li><a href="#built-with">构建于</a></li>
      </ul>
    </li>
    <li>
      <a href="#开始构建">开始构建</a>
      <ul>
        <li><a href="#所需环境">所需环境</a></li>
        <li><a href="#进行配置">进行配置</a></li>
      </ul>
    </li>
    <li><a href="#用途">用途</a></li>
    <li><a href="#贡献">贡献</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#联系">联系</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## 关于本项目

这是一个小型文件中转站，通过将它部署到云服务器中，即可实现文件的中转，从而实现文件的分享或者跨平台传输的功能。


<p align="right">(<a href="#swift-send">back to top</a>)</p>

### 构建于

* [![JDk8][JDK8.com]][JDK8-url]
* [![Spring][Spring.com]][Spring-url]
* [![Spring Boot][SpringBoot.com]][SpringBoot-url]
* [![MySQL][MySQL.com]][MySQL-url]
* [![Mybatis Plus][MybatisPlus.com]][MybatisPlus-url]
* [![IntelliJ IDEA][IntelliJ IDEA.com]][IntelliJ IDEA-url]

<p align="right">(<a href="#swift-send">back to top</a>)</p>



<!-- 开始构建 -->


### 所需环境

* JDK8 本项目由JDK8构建，请在运行本项目前确保您的电脑已安装JDK8，若您使用的是IntelliJ
  IDEA，您可以很方便的在`Project Structure`中配置JDK版本。
* MySQL 本项目使用MySQL数据库，请在运行本项目前确保您可以顺利连接到MySQL数据库。
* Maven 本项目使用Maven构建，初次打开项目时，IntelliJ IDEA会自动下载Maven依赖，若您的IntelliJ
  IDEA没有识别到Maven，请右键项目，选择`Add Framework Support`，选择`Maven`，然后点击`OK`。
  若在Maven下载依赖时出现问题（大部分国内用户都会出现这个问题），为了一劳永逸，建议您在IntelliJ
  IDEA的`File`->`Settings`->`Build,Execution,Deployment`->`Build Tools`->`Maven`中配置Maven。
  通过在您电脑用户目录下的`.m2`文件夹中找到`settings.xml`文件（若没有则创建一个该名的文件），在`<Mirrors>`内部添加以下内容：

```xml

<mirrors>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云公共仓库</name>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
</mirrors>
```

再在该设置目录下的`User Settings File`中选择刚刚创建的`settings.xml`文件，勾选`Override`，点击`OK`即可，再次重启IntelliJ
IDEA，Maven依赖就会自动下载了。
若`pom.xml`依然爆红，尝试再次加载。

### 进行配置

* **数据库配置** </p>
  无论你使用什么数据库**管理工具**，请在数据库中创建一个容易被记住的数据库名，例如`swift`，然后在导入本仓库的`sql`
  文件夹中的[`database.sql`文件](https://github.com/riverify/swift-send/blob/main/sql/db.sql)。
  之后需要回到本项目的`src/main/resources`目录下，找到`application.yml`文件，补充数据库的连接信息。


* **文件存储位置配置** </p>
  该系统由于需要存储用户上传的图片，所以需要配置一个文件存储的位置，然后在`application.yml`的文件存储位置配置信息中配置它。

自此，配置基本完成，你可以顺利在本地运行项目了，通过运行在`src/main/java/com/swift`目录下的`SwiftSendApplication.java`
即可启动本项目。

对于希望在服务器上运行，同理修改配置文件（**_注意文件存储位置配置信息_**）后在IntelliJ IDEA终端输入:

`mvn package -Dmaven.test.skip=true`

即可完成打包，云服务器部署请自行利用互联网搜索。

[示例网站](https://fubukiss.com)

<!-- USAGE EXAMPLES -->

## 用途

本项目是一个文件中转系统，实现原理较为简单，通过将文件上传到服务器，然后将文件的下载链接发送给对方，对方通过访问该链接即可下载文件。
适用于个人文件分享，跨平台文件传输等场景。用户先上传文件，之后会获取一个唯一的文件密钥，这个密钥和对应的文件会在24个小时后释放，
密钥一旦被使用也会立即实效并删除文件。其中的文字分享功能还未实现，后续会加上。

[![](https://github.com/riverify/swift-send/blob/main/img/dishes.png?raw=true)](https://github.com/riverify/swift-send/blob/main/img/dishes.png?raw=true)
[![](/home/river/code/IdeaProjects/swift-send/img/login2.png)](https://github.com/riverify/swift-send/blob/main/img/login2.png?raw=true)
[![](https://github.com/riverify/swift-send/blob/main/img/choose.png?raw=true)](https://github.com/riverify/swift-send/blob/main/img/choose.png?raw=true)

<p align="right">(<a href="#swift-send">back to top</a>)</p>




<!-- CONTRIBUTING -->

## 贡献

**贡献是使开源社区成为一个学习、激励和创造的奇妙场所的原因。我们非常感谢您的任何贡献。**

如果你有什么建议可以让这个项目变得更好，请[fork](https://github.com/riverify/swift-send/fork)该版本并**创建一个PR**。
如果在学习中遇到了一些困难，你也可以[在这里](https://github.com/riverify/swift-send/issues)**提交一个issue**，我会尽快回复你。
如果它对你有帮助，请**star💫**它，再次感谢!

关于如何贡献的更多信息，请查看[CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426)。

1. [Fork](https://github.com/riverify/swift-send/fork) the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#swift-send">back to top</a>)</p>



<!-- LICENSE -->

## License

Distributed under the [ApacheApache-2.0 license](https://github.com/riverify/swift-send/blob/main/LICENSE).
See `LICENSE.txt` for more information.

<p align="right">(<a href="#swift-send">back to top</a>)</p>



<!-- CONTACT -->

## 联系

riverify - [@riverify](https://github.com/riverify) - https://github.com/riverify

项目链接: [🔗https://github.com/riverify/swift-send](https://github.com/riverify/swift-send)

<p align="right">(<a href="#swift-send">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

以下资源亦可帮助学习：

* [Java项目实战《瑞吉外卖》](https://www.bilibili.com/video/BV13a411q753)
* [Best-README-Template](https://github.com/othneildrew/Best-README-Template)
* [toBeBetterJavaer](https://github.com/itwanger/toBeBetterJavaer)
* [黑马程序员Java学习路线图](https://www.bilibili.com/read/cv9965357)
* [build-your-own-x自己造轮子](https://github.com/codecrafters-io/build-your-own-x)
* [编程猫codingmore](https://github.com/itwanger/codingmore-learning)

<p align="right">(<a href="#swift-send">back to top</a>)</p>

## End

<p align="right">(<a href="#swift-send">back to top</a>)</p>




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/riverify/swift-send.svg

[contributors-url]: https://github.com/riverify/swift-send/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/riverify/swift-send.svg

[forks-url]: https://github.com/riverify/swift-send/network/members

[stars-shield]: https://img.shields.io/github/stars/riverify/swift-send.svg

[stars-url]: https://github.com/riverify/swift-send/stargazers

[issues-shield]: https://img.shields.io/github/issues/riverify/swift-send.svg

[issues-url]: https://github.com/riverify/swift-send/issues

[license-shield]: https://img.shields.io/github/license/riverify/swift-send.svg

[license-url]: https://github.com/riverify/swift-send/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/riverify

[product-screenshot]: images/screenshot.png

[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white

[Next-url]: https://nextjs.org/

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[React-url]: https://reactjs.org/

[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D

[Vue-url]: https://vuejs.org/

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white

[Angular-url]: https://angular.io/

[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00

[Svelte-url]: https://svelte.dev/

[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white

[Laravel-url]: https://laravel.com

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white

[Bootstrap-url]: https://getbootstrap.com

[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white

[JQuery-url]: https://jquery.com

[Java.com]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white

[Java-url]: https://www.java.com/en/

[Python.com]: https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white

[Python-url]: https://www.python.org/

[Spring.com]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white

[Spring-url]: https://spring.io/

[SpringBoot.com]: https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot

[SpringBoot-url]: https://spring.io/projects/spring-boot

[MyBatis.com]: https://img.shields.io/badge/MyBatis-2779BD?style=for-the-badge&logo=mybatis&logoColor=white

[MyBatis-url]: https://mybatis.org/mybatis-3/

[MySQL.com]: https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white

[MySQL-url]: https://www.mysql.com/

[MybatisPlus.com]: https://img.shields.io/badge/MyBatis_Plus-2779BD?style=for-the-badge&logo=mybatis&logoColor=white

[MybatisPlus-url]: https://mybatis.plus/

[Redis.com]: https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white

[Redis-url]: https://redis.io/

[ElementUI.com]: https://img.shields.io/badge/Element_UI-4FC08D?style=for-the-badge&logo=elementdotio&logoColor=white

[ElementUI-url]: https://element.eleme.io/

[JDK8.com]: https://img.shields.io/badge/Java_8-ED8B00?style=for-the-badge&logo=java&logoColor=white

[JDK8-url]: https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

[IntelliJ IDEA.com]: https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white

[IntelliJ IDEA-url]: https://www.jetbrains.com/idea/
