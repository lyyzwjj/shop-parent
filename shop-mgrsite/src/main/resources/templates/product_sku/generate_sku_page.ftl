<table class="table table-bordered sku-table">
    <thead>
    <tr>
        <th class="actions-col"></th>
        <th>SKU 编号</th>
        <#list skuPropertyList as skuProperty>
            <th>${skuProperty.name}</th>
        </#list>
        <th>售价</th>
    </tr>
    </thead>
    <tbody>
    <#list skuList as sku>
        <tr>
            <td class="actions-col">
                <button type="button" class="btn btn-default js-remove">
                    移除
                </button>
            </td>
            <td>
                <input class="form-control" name="productSkuList[${sku_index}].code" value="${sku['code']}">
            </td>
            <#list skuPropertyList as skuProperty>
                <th>
                    ${sku['${skuProperty.id}']}
                    <input type="hidden" name="productSkuList[${sku_index}].productSkuPropertyList[${skuProperty_index}].skuProperty.id" value="${skuProperty.id}">
                    <input type="hidden" name="productSkuList[${sku_index}].productSkuPropertyList[${skuProperty_index}].value" value="${sku['${skuProperty.id}']}">
                </th>
            </#list>
            <td>
                <input class="form-control price-input" name="productSkuList[${sku_index}].price" value="${sku['price']?c}">
            </td>
        </tr>
    </#list>
    </tbody>
</table>
<script>
    $('.sku-table').on('click', '.js-remove', function(){
        $(this).closest('tr').remove();
    });

</script>