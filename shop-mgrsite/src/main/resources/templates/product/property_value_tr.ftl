<#list propertyList as property>
    <tr>
        <td>
            ${property.name}
            <input type="hidden"  name="productPropertyValueList[${property_index}].name" value="${property.name}">
        </td>
        <td>
            <#if property.type==0>
                <input class="form-control" name="productPropertyValueList[${property_index}].value">
            <#else>
                <select class="form-control" name="productPropertyValueList[${property_index}].value">
                    <#list property.propertyValueList as propertyValue>
                        <option value="${propertyValue.value}">${propertyValue.value}</option>
                    </#list>
                </select>
            </#if>

        </td>
    </tr>
</#list>