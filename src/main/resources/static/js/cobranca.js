
//  ID            toda vez que   V  acontecer eu quero executar uma função que recebe evento como parametro              
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
    
	//variavel do tipo jquery 
	//por isso pode utilizar essa funcao data para recuperar os atributos do tipo data do button no caso código
	var button = $(event.relatedTarget);	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao')
	
	var modal = $(this);
	//para pegar o form do 'DialogoConfirmacaoExclusao'
	var form = modal.find('form');
	
	//para pegar o action do 'DialogoConfirmacaoExclusao'
	var action = form.data("url-base");
	
	
	//não terminar com barra/CONDIÇÃO
	
	if(!action.endsWith('/')) {
		action += '/';
	}
	
	// editar o atributo action e adiciona o código
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('tem certeza que deseja excluir o título <strong>'+descricaoTitulo+'</strong>?');
	
});