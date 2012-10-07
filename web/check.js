function checkRequiredField()
{
    var desc = document.addTempForm.desc.value;
    var price = document.addTempForm.price.value;
    if ( desc == '' )
        {
            alert ( "Temp Item Description is required!");
            return false;
        }
        if ( price == '' )
        {
            alert ( "Temp Item Price is required!");
            return false;
        }
    return true;
}