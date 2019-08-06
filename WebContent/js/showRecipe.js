function showRecipe(selected,selector){
	$.ajax({
		type:'POST',
		dataType:'text',
		data:'recipe_id='+selected,
		url:'showRecipe',
		success: function(res){
			$(selector).html(res);
		}
	})
}