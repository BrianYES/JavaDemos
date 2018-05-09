<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<#-- 注释部分 -->
<#-- 下面使用插值 -->
<#assign user="Brian"/>
<h1>Welcome ${user} !</h1>
<p>We have these animals: </p>
<ul>
<#-- 使用 FTL 指令 -->
<#list animals as being>
    <li>${being.name} for ${being.price} Euros </li>
</#list>
</ul>
</body>
</html>