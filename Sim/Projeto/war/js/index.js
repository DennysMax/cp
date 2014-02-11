getJSON = function( file ) {
	var request = new XMLHttpRequest();
	request.open("GET", file, false);
	request.send(null);
	return  request.responseText;
}

getJSONParsed = function (file) {
	return JSON.parse( getJSON( file ) );
}


var data;
var cart = new Array();
var ver = "10";

$(document).ready( function() {

	$('.menu').click( function() {
		var dataLoad = $(this).attr('data-load');

		if ( dataLoad )
		{
			data = getJSONParsed('content/' + dataLoad + '.json');
			var conteudo = new EJS({ url: "template/produtos.ejs?" + ver }).render( data );
			$(".direita").html(conteudo);
		}

	});

	$('body').on('click', 'h4', function() {
		var dataAdd = $(this).attr('data-add');

		if ( dataAdd )
		{
			cart.push( data[ dataAdd ] );
			var conteudo = new EJS({ url: "template/carrinho.ejs?" + ver }).render( cart );
			$(".carrinho").html(conteudo);
		}

	});

	$('body').on('click', 'b', function() {
		var dataRem = $(this).attr('data-rem');

		if ( dataRem )
		{
			cart.splice( dataRem, 1 );
			var conteudo = new EJS({ url: "template/carrinho.ejs?" + ver }).render( cart );
			$(".carrinho").html(conteudo);
		}

	});

});

