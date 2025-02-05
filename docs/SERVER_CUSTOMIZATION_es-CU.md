# Personalizar un servidor de SORMAS

## Contenido
* [Configuración del servidor](#server-configuration)
* [Importar datos de infraestructura](#importing-infrastructure-data)
* [Configuración de enfermedades](#disease-configuration)
* [Configuración de características](#feature-configuration)

## Configuración del servidor
Después de instalar un servidor de SORMAS, puede personalizar varias configuraciones que definen cómo funciona y se configura SORMAS. Esto se hace en el archivo **sormas.properties** que puede encontrar en su carpeta de dominio. Este archivo contiene explicaciones para cada propiedad y también un valor predeterminado en caso de que desee revertir cualquier cambio que haya realizado.

La mayoría de estas propiedades están comentadas (indicado por un *#* delante de su nombre y valor) porque el valor predeterminado debería funcionar para la mayoría de los servidores. Si desea cambiar estas propiedades, puede eliminar el *#* y especificar un valor válido. Recomendamos asignar valores a las propiedades que no están comentadas de forma predeterminada porque son específicas de su servidor (por ejemplo, la configuración regional/idioma predeterminado, el centro del país o provincia en que está utilizando SORMAS, y la URL que conduce al archivo .apk para móviles).

Tenga en cuenta que este archivo contiene todas las propiedades que existían en la versión de SORMAS que instaló inicialmente en su servidor. Recomendamos leer las notas de las nuevas versiones para mantenerse actualizado sobre las nuevas propiedades o si los valores esperados de las existentes han cambiado. En cualquiera de estos casos, debe actualizar manualmente el archivo de propiedades, e insertar la nueva propiedad o cambiar el valor existente por uno que sea compatible. Las notas de la versión le darán instrucciones sobre cómo hacer esto.

Actualmente se pueden configurar las siguientes propiedades:

* **Configuración regional predeterminada** `country.locale`: Esta es la configuración regional que usa su servidor siempre que el usuario no la haya sobrescrito en su configuración. Afecta tanto el idioma en que se muestra SORMAS como, por ejemplo, los formatos de fecha.
* **Prefijo EPID** `country.epidprefix`: SORMAS genera automáticamente números EPID para los nuevos casos. Este es el prefijo que usa su país para todos estos números. La mayoría de las veces será algún tipo de código de país, y debe tener tres caracteres.
* **Centro/zoom de país** `country.center.latitude`, `country.center.longitude`, y `map.zoom`: Estas son las coordenadas geográficas del centro geográfico del país o la provincia en que está utilizando SORMAS. Se utilizan para establecer la ubicación inicial de los mapas utilizados en los tableros de control y las estadísticas.
* **URL de la aplicación** `app.url`: El directorio de su servidor donde se almacena el archivo .apk para móviles que se utiliza para actualizar automáticamente la aplicación de Android cuando se publica una nueva versión. Debería poder copiar el ejemplo dado en el archivo de propiedades, y reemplazar solamente el marcador de posición SERVER-NAME.
* **Rutas de archivo** `documents.path`, `temp.path`, `generated.path`, y `custom.path`: Las carpetas en las que SORMAS almacena archivos, ya sea temporalmente durante la exportación de casos, o permanentemente como plantillas de importación o documentos. Los archivos en temp.path se eliminan automáticamente a la medianoche. Los archivos en custom.path se pueden utilizar para personalizar la página de inicio de sesión, por ejemplo, para proporcionar inicios de sesión predeterminados para servidores demo, o agregar colaboradores adicionales a la barra lateral derecha.
* **Clasificación automática de casos** `feature.automaticcaseclassification`: Determina si SORMAS clasifica automáticamente los casos en función de una serie de criterios definidos en el código.
* **Ajustes de correo electrónico** `email.sender.address`, y `email.sender.name`: La dirección de correo electrónico y el nombre de remitente que se deben usar cuando SORMAS envía correos electrónicos, por ejemplo, para notificar a los usuarios sobre eventos específicos.
* **Configuración de SMS** `sms.sender.name`, `sms.auth.key`, y `sms.auth.secret`: Además de correos electrónicos, SORMAS también admite el envío automático de SMS a los usuarios simultáneamente (por ejemplo, cuando un caso se ha clasificado como confirmado). El proveedor de SMS que utiliza SORMAS es la API SMS de Vonage (https://www.vonage.com/communications-apis/sms/). Si tiene una cuenta allí, puede usar su clave y secreto aquí para permitir el envío de SMS. Dejar estas propiedades sin especificar deshabilitará esta característica.
* **Separador CSV** `csv.separator`: El separador que los archivos CSV deben usar para separar columnas. Esto depende de la configuración regional de su servidor. La mayoría de los sistemas pueden usar el valor predeterminado (*,*), pero, por ejemplo, los sistemas alemanes deben configurarse para usar *;*.
* **Umbral de similitud de nombres** `namesimilaritythreshold`: Esto se usa al comparar casos o contactos para encontrar duplicados en el sistema, ya sea en retrospección, o durante la creación o importación. Cuanto mayor sea el valor, más restrictivo será el algoritmo, es decir, se encontrarán menos duplicados potenciales. Recomendamos experimentar con esta configuración para ver qué valor funciona para su país e idioma.
* **Modo de desarrollador** `devmode`: Habilitar el modo de desarrollador le dará acceso a una pestaña en el menú Configuración que permite a los administradores crear casos y contactos ficticios para llenar rápidamente la base de datos. Esto solo debe ser utilizado en sistemas de desarrollo o demostración, y debe dejarse desactivado para servidores de producción.
* **Umbral de sincronización de infraestructura** `infrastructuresyncthreshold`: La sincronización de los datos de infraestructura con las aplicaciones móviles (por ejemplo, provincias o centros de salud) se realiza por partes para evitar timeouts de conexión. Si espera que sus usuarios tengan una conexión a Internet muy mala, reducir este umbral podría facilitarles la sincronización de estos datos.
* **Umbrales para archivar** `daysAfterCaseGetsArchived`, y `daysAfterEventGetsArchived`: El número de días sin ningún cambio después de los cuales los casos/eventos se archivan automáticamente (es decir, ya no se mostrarán en los directorios normales, pero seguirán contando para estadísticas o recuentos en el tablero de control, y los usuarios con el derecho de usuario correspondiente aún podrán verlos). Si se especifican como 0, el archivado automático se deshabilita.
* **Ejecutable Rscript** `rscript.executable`: La ubicación del ejecutable Rscript. Si ha instalado Rscript en su servidor y especifica la ruta aquí (el valor predeterminado debería funcionar para sistemas Linux siempre que haya utilizado la ruta de instalación predeterminada), los diagramas de red para las cadenas de transmisión se mostrarán en la aplicación web.
* **Interfaz de diario de síntomas**: Propiedades utilizadas para conectarse a un servicio de diario de síntomas externo. `interface.symptomjournal.url` es la URL del sitio web al que debe conectarse SORMAS; `interface.symptomjournal.authurl` es la URL utilizada para autenticar SORMAS en el servicio externo; `interface.symptomjournal.clientid` y `interface.symptomjournal.secret` son las credenciales utilizadas para el proceso de autenticación. Se puede crear automáticamente un usuario predeterminado al inicio utilizando `interface.symptomjournal.defaultuser.username` y `interface.symptomjournal.defaultuser.password`. Este usuario puede ser utilizado por el sistema de diario de síntomas para conectarse a SORMAS.
* **Interfaz de diario de paciente** Propiedades utilizadas para conectarse a un servicio de diario de paciente externo. `interface.patientdiary.url` es la URL del sitio web al que debe conectarse SORMAS; `interface.patientdiary.probandsurl` es la URL del sitio web al que SORMAS puede enviar notificaciones; `interface.patientdiary.authurl` es la URL a través de la cual SORMAS puede obtener una autorización para el diario de paciente externo; `interface.patientdiary.email` y `interface.patientdiary.password` son las credenciales utilizadas por SORMAS para autenticarse en el diario de paciente externo; se puede crear automáticamente un usuario predeterminado al inicio utilizando `interface.patientdiary.defaultuser.username` y `interface.patientdiary.defaultuser.password`. Este usuario puede ser utilizado por el sistema de diario de paciente para conectarse a SORMAS.
* **Marca personalizada**: Propiedades utilizadas para aplicar una marca personalizada a SORMAS que anula su nombre y logotipo predeterminado. El uso de estas propiedades también modifica la barra lateral y le agrega otra área personalizable. Si desea utilizar esta función, asigne `true` a `custombranding`. `custombranding.name` es el nombre que desea usar, `custombranding.logo.path` es la ruta al logo que debe usarse.
* **Geocodificación** Propiedades utilizadas para integrar un servicio de geocodificación externo para obtener las coordenadas geográficas de las direcciones.
  * `geocodingServiceUrlTemplate` es la URL para buscar detalles de las direcciones, los marcadores de posición `${street}`, `${houseNumber}`, `${postalCode}`, y `${city}` se reemplazarán con los campos de dirección reales al buscar;
  * `geocodingLongitudeJsonPath` and `geocodingLatitudeJsonPath` se utilizan para obtener la longitud y latitud de la dirección en el resultado de la solicitud al servicio de geocodificación.
* **Proveedor de autenticación**: Permite al usuario elegir la forma de autenticación para SORMAS y todos sus clientes de terceros. Valores admitidos  `SORMAS` (predeterminado) y `KEYCLOAK`
* **Sincronización de usuarios del proveedor de autenticación al inicio**: Habilita la sincronización de usuarios asíncronos cuando se inicia el sistema. Dado que la sincronización de usuarios es necesaria principalmente para una sincronización inicial, se recomienda deshabilitar/eliminar esta propiedad una vez se haya realizado una sincronización inicial. La sincronización de usuarios funcionará de manera similar a la sincronización manual de usuarios:
  * crea todos los usuarios que faltan en el proveedor de autenticación externo
  * actualiza todos los usuarios existentes en el proveedor de autenticación externo
  * conserva la contraseña del usuario si el usuario no existe en el proveedor de autenticación externo
  * no sobrescribirá la contraseña del usuario si el usuario ya está en el proveedor de autenticación externo (la búsqueda de coincidencias se realiza mediante el nombre de usuario sin distinción entre mayúsculas y minúsculas)
  * solo sincronizará usuarios activos (los usuarios inactivos se sincronizan automáticamente cuando se activan manualmente)
  * se habilita a través de una propiedad en sormas.properties `authentication.provider.userSyncAtStartup` (por defecto está deshabilitada)

### Página de inicio de sesión personalizada
Al configurar el servidor se crea un directorio de archivos personalizado (muy probablemente `/opt/sormas/custom`). Puede ajustar los archivos `login*.html` en ese directorio para personalizar la página de inicio de sesión.

### Archivos de descarga personalizados en la sección Acerca de
Puede crear una subcarpeta `aboutfiles` en el directorio personalizado mencionado anteriormente (por ejemplo, `/opt/sormas/custom/aboutfiles`). Cualquier archivo en ese directorio estará disponible en la sección Acerca de de la interfaz.

## Importar datos de infraestructura
Cuando inicia un servidor de SORMAS por primera vez, se generan algunos datos de infraestructura predeterminados para garantizar que el servidor sea utilizable y se puedan crear los usuarios predeterminados. Se recomienda (y, a menos que esté trabajando en un servidor demo, es necesario) archivar estos datos predeterminados, e importar los datos de infraestructura oficiales del país o parte del país en que desea utilizar SORMAS.

### Importar
SORMAS divide por defecto los datos de infraestructura en cuatro categorías. Partiendo de la división administrativa más alta, estos son: *Provincias*, *Municipios*, *Áreas de salud*, y *Centros de salud*. Adicionalmente, *Puntos de entrada* representa lugares, como puertos y aeropuertos, donde hay una entrada frecuente de personas al país, y *Laboratorios* son técnicamente centros de salud que se utilizan específicamente para realizar pruebas de muestras.

Para importar sus datos para una de estas divisiones administrativas, inicie sesión como usuario administrador predeterminado y abra el menú **Configuración**. Abra cualquiera de las pestañas para los datos de infraestructura que desea importar, y haga clic en el botón **Importar** en la parte superior derecha. Puede descargar una guía de importación desde la ventana emergente que se abrirá, que contiene instrucciones detalladas sobre el proceso de importación y los pasos que debe seguir para importar sus datos correctamente.

Asegúrese de comenzar siempre con la división administrativa más alta al importar, es decir, las provincias, y continúe hasta la más baja, ya que las divisiones inferiores suelen contener referencias a divisiones superiores.

### Archivar
Después de importar sus datos de infraestructura, debe archivar los datos predeterminados, a menos que desee que aparezcan en su aplicación. Para hacerlo, vuelva a abrir el menú **Configuración** y la pestaña de los datos de infraestructura que desea archivar. Puede usar el filtro de texto en la parte superior de la pantalla para escribir el nombre de los datos predeterminados, luego haga clic en el ícono de edición a la derecha, y en la ventana emergente que se abre, haga clic en **Archivar** y confirme su elección.

Después de archivar los datos de infraestructura predeterminados, es posible que desee editar los usuarios predeterminados y asignarlos a las divisiones administrativas que haya importado. Para hacerlo, vaya al menú **Usuario** y haga clic en el ícono de edición debajo del usuario que desea reasignar.

## Configuración de enfermedades
SORMAS admite una amplia gama de enfermedades, y no todas ellas pueden ser relevantes para cualquier instancia de SORMAS o pueden usarse en un contexto diferente. Es posible ajustar las siguientes variables que definen cómo se manejan las diferentes enfermedades:

* Si la enfermedad es **activa**, es decir, se usa en esta instancia de SORMAS
* Si la enfermedad es una enfermedad **primaria**, es decir, está habilitada para la vigilancia de casos; las enfermedades no primarias aún se pueden utilizar para pruebas de patógenos.
* Si la enfermedad es **basada en casos**; si no, solo está habilitada para informes de casos agregados
* Si **el seguimiento de contactos está habilitado**
* La **duración del seguimiento de contactos**

En este momento, desafortunadamente, no es posible cambiar estas variables desde la interfaz de usuario; se requiere **acceso directo a la base de datos**. Si tiene este acceso, puede editar las entradas de la tabla *diseaseconfiguration* de acuerdo a sus necesidades. 

**MUY IMPORTANTE:** Siempre que edite una entrada en esta tabla, también debe configurar manualmente *changedate* como la fecha y hora actuales. Esto es necesario para que la aplicación de móviles sincronice los cambios y utilice la configuración de enfermedades editada.

## Configuración de características
Algunas de las características de SORMAS se pueden habilitar o deshabilitar para personalizar aún más el sistema. En este momento, desafortunadamente, no es posible cambiar estas variables desde la interfaz de usuario; se requiere **acceso directo a la base de datos**. Si tiene este acceso, puede editar las entradas de la tabla *featureconfiguration*. Hay una entrada para cada característica configurable en esta tabla, y puede especificar el valor de la columna *enabled* como *true* para habilitarla, y como *false* para deshabilitarla. Las columnas *region*, *district*, *disease*, y *enddate* actualmente solo se aplican a la característica de listado de líneas y definen el ámbito en que se utiliza el listado de líneas. El listado de líneas se puede configurar desde la interfaz de usuario y no es necesario editarlo manualmente en la base de datos. 

**MUY IMPORTANTE:** Siempre que edite una entrada en esta tabla, también debe configurar manualmente *changedate* como la fecha y hora actuales. Esto es necesario para que la aplicación de móviles sincronice los cambios y utilice la configuración de enfermedades editada.

Las siguientes características se pueden configurar actualmente:

* **Vigilancia de casos** `CASE_SURVEILANCE`: El módulo central de SORMAS que permite la creación y gestión de casos de enfermedad sospechada o confirmada.
* **Rastreo de contactos** `CONTACT_TRACING`: Gestión y seguimiento de contactos de casos de enfermedades.
* **Gestión de muestras** `SAMPLES_LAB`: Gestión de muestras de casos, contactos, o participantes de eventos, y la documentación de las pruebas de patógenos realizadas sobre estas muestras.
* **Vigilancia de eventos** `EVENT_SURVEILLANCE`: Creación y gestión de eventos y participantes de eventos para identificar posibles brotes o focos de enfermedades.
* **Informes agregados** `AGGREGATE_REPORTING`: Permite recolectar números de casos de una serie de enfermedades adicionales para las que no se utiliza la vigilancia basada en casos. Comúnmente conocido como mSers en países africanos.
* **Informes semanales** `WEEKLY_REPORTING`: Permite a los usuarios de dispositivos móviles confirmar la cantidad de casos que han recolectado semanalmente, y a los usuarios web ver un resumen general de si los usuarios de dispositivos móviles han enviado o no sus informes y cuántos casos han informado.
* **Gestión clínica** `CLINICAL_MANAGEMENT`: Habilita el módulo de gestión clínica de casos que permite recolectar prescripciones y tratamientos así como visitas del médico en un contexto clínico.
* **Compartición nacional de casos** `NATIONAL_CASE_SHARING`: Permite a los usuarios con los derechos correspondientes poner los casos a disposición de todo el país, es decir, otros usuarios verán estos casos incluso si no pertenecen a sus jurisdicciones.
* **Generación de tareas (Vigilancia de casos)** `TASK_GENERATION_CASE_SURVEILLANCE`: Habilita o deshabilita la generación automática de tareas asociadas con la vigilancia de casos, especialmente las tareas de *Investigación de caso* que normalmente se generan al crear un nuevo caso.
* **Generación de tareas (Rastreo de contactos)** `TASK_GENERATION_CONTACT_TRACING`: Habilita o deshabilita la generación automática de tareas asociadas con el rastreo de contactos, especialmente las tareas de *Investigación de contacto* que normalmente se generan al crear un nuevo contacto y las tareas de *Seguimiento de contacto* que se crean una vez al día para cada contacto que está en seguimiento.
* **Generación de tareas (Vigilancia de eventos)** `TASK_GENERATION_EVENT_SURVEILLANCE`: Habilita o deshabilita la generación automática de tareas asociadas con la vigilancia de eventos.
* **Generación de tareas (General)** `TASK_GENERATION_GENERAL`: Habilita o deshabilita la generación automática de tareas que no están asociadas directamente con uno de los otros tres tipos de tareas descritos anteriormente, por ejemplo, la tarea *Generación de informe semanal* que solicita a los usuarios de dispositivos móviles que envíen sus informes semanales.
* **Campañas** `CAMPAIGNS`: El módulo de campañas permite recolectar datos flexibles que se pueden personalizar utilizando el formato JSON. Actualmente, esto está muy orientado a las campañas de vacunación en Afganistán, pero en el futuro también se podrá utilizar de una manera más genérica en otros países.
* **Infraestructura de zona** `INFRASTRUCTURE_TYPE_AREA`: Habilita un nivel de infraestructura adicional por encima de la provincia que se llama zona por defecto. Actualmente solo se usa en el módulo de campañas.
* **Seguimiento de casos** `CASE_FOLLOWUP`: Habilita el módulo de seguimiento de contactos para los casos también a fin de permitir una documentación diaria más detallada de los síntomas.
* **Listado de líneas** `LINE_LISTING`: Si el uso del listado de líneas para la entrada de casos está habilitado o no en la jurisdicción especificada para la enfermedad especificada. Configurable desde la interfaz de usuario, no se necesita interacción con la base de datos.
* **Documentos** `DOCUMENTS`: Habilita el almacenamiento de documentos.

## Configuración de proxy
Algunas integraciones de SORMAS admiten la configuración de proxy:
* **Interfaz de diario de paciente**
* **Geocodificación**
* **SORMAS 2 SORMAS**

El proxy se puede configurar a través de las siguientes propiedades del sistema que se pueden pasar como argumentos de JVM al servidor:
* `org.jboss.resteasy.jaxrs.client.proxy.host`
* `org.jboss.resteasy.jaxrs.client.proxy.port`
* `org.jboss.resteasy.jaxrs.client.proxy.scheme`