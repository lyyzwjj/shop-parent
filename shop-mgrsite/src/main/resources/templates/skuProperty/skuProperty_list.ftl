<div class="panel panel-default">
    <div class="panel-body">
        <h4>分类属性：</h4>
        <div class="modal-body">
            <table class="table table-hover">
                <tr>
                    <th>属性名</th>
                    <th>属性类型</th>
                    <th>操作</th>
                </tr>
                <tbody id="skuPropertyTbody">
                    <#list skuPropertyList as skuProperty>
                        <tr>
                            <td>${skuProperty.name}</td>
                            <td>
                                <a href="javascript:showskuPropertyValue(${skuProperty.id});">查看属性值</a>
                            </td>
                            <td>
                                <a href="javascript:;"onclick="editskuProperty(this)" data-json='${skuProperty.jsonData!""}'>编辑</a>
                                /
                                <a href="javascript:;" onclick="deleteskuProperty(${skuProperty.id})">删除</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>