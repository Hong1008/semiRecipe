/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */


CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_config.html

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbar = [

        ['Font', 'FontSize'],

        ['BGColor', 'TextColor' ],

        ['Bold', 'Italic', 'Strike', 'Superscript', 'Subscript', 'Underline', 'RemoveFormat'],   

        ['BidiLtr', 'BidiRtl'],

        ['Image', 'SpecialChar', 'Smiley'],

        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],

        ['Blockquote', 'NumberedList', 'BulletedList'],

        ['Link', 'Unlink'],

        ['Source'],

        ['Undo', 'Redo']

];
	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	
//	config.filebrowserUploadUrl = '${pageContext.request.contextPath}/recipe/reviewimg';
	
	
	
	config.language = 'ko';
	config.font_names = '맑은 고딕; 돋움; 바탕; 돋음; 궁서; Nanum Gothic Coding; Quattrocento Sans;' + CKEDITOR.config.font_names;
};

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.extraPlugins = 'uploadimage'; 
	config.imageUploadUrl = '/semiRecipe/ckeditor/upload.jsp?type=Images';
	config.toolbarCanCollapse = true; 
	config.filebrowserUploadMethod = 'form';
	config.filebrowserUploadUrl = '/semiRecipe/ckeditor/upload.jsp';
	config.filebrowserImageBrowseUrl = '/semiRecipe/ckeditor/upload.jsp?type=Images';
};

config.imageUploadUrl = "/semiRecipe/ckeditor/upload.jsp"

// add to config.js
CKEDITOR.on( 'instanceReady', function( ev ) {
	// code for fileupload and response
	ev.editor.on( 'fileUploadRequest', function( evt ) {
		var fileLoader = evt.data.fileLoader,
		formData = new FormData(),
		xhr = fileLoader.xhr;
		xhr.open( 'POST', fileLoader.uploadUrl, true );
		formData.append( 'file[]', fileLoader.file, fileLoader.fileName );
		fileLoader.xhr.send( formData );
		// Prevent default behavior.
		evt.cancel();
	}, null, null, 4 ); // Listener with priority 4 will be executed before priority 5.

	ev.editor.on( 'fileUploadResponse', function( evt ) {
			// Prevent the default response handler.
			evt.stop();
			// Get XHR and response.
			var data = evt.data,
			xhr = data.fileLoader.xhr,
			response = xhr.responseText;
			if ($(response).find('div.updated').html()) {
				resphtml = $(response).find('div.updated').html();
				url = $("a",resphtml).attr("href");
				data.url = url;
				$('div.bodycontent').before('<div class="updated"><p>' + resphtml + '</p></div>');
				return;
			}

			if ($(response).find('div.error').html()) {
				resphtml = $(response).find('div.error').html();
				msg = resphtml;
				data.message = msg;
				$('div.bodycontent').before('<div class="error"><p>' + resphtml + '</p></div>');
				evt.cancel();
				return;
			}
	} );
}); 


