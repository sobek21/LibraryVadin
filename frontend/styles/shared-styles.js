// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
  <style>
    html {
      --lumo-primary-color: hsl(214, 71%, 39%);
    }

    [theme~="dark"] {
    }
  </style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
