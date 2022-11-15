<#assign val_str="this is a string."/>
<#assign val_bool=false/>
<#assign val_float=123.321/>
<#assign val_int=23/>
<#assign seq=["1", "2", "3"]/>

<#setting number_format="currency"/>
<#--<#setting boolean_format=-->

${val_str}
${val_bool?c}
${val_float?string.percent}
${val_int}

<#if val_int gt 100>
    val_int大于100
<#else >
    val_int小于100
</#if>

<#list seq as val>
    ${val}
</#list>
${seq[1]}