<div class="modal-body">

    <input type="hidden" name="skuPropertyId" value="${skuPropertyId}"/>
    <div class="form-group">
        <label>
            属性名
        </label>
        <div id="valueDiv">
            <#list skuPropertyValueList as skuPropertyValue>
            <div style="height: 50px;" class="skuPropertyValueDiv">
                <input type="hidden" name="id" value="${skuPropertyValue.id}"/>
                <input type="text" class="form-control" name="value" value="${skuPropertyValue.value}" style="width: 200px;margin-bottom: 5px;float: left;">
                <h3 style="float: left;margin: 0;">
                    <span class="label label-primary" style="cursor: pointer;" onclick="deleteskuPropertyValue(this)" data-id="${skuPropertyValue.id}">-</span>
                </h3>
            </div>
            </#list>
        </div>
        <h3><span class="label label-primary" style="cursor: pointer;" onclick="addLine()">+</span></h3>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" onclick="saveskuPropertyValue()">保存</button>
        </div>
    </div>
</div>