###     klv-pu-back

CRUD con base de Datos Postgresql para Actividades y Elementos de construccion. 

### **Descripción Funcional de la Aplicación**

La aplicación permitirá gestionar actividades relacionadas con proyectos de construcción (o cualquier dominio similar) mediante la relación de elementos y cantidades. Está diseñada para ser modular y flexible, permitiendo integrarse con cualquier frontend o backend. A continuación, se describe la estructura de las tablas, las relaciones entre ellas, y las consultas necesarias para crearlas y poblarlas con datos de ejemplo.

---

### **Estructura de las Tablas**

1. **Tabla `Actividades`**
   - Contiene información general sobre actividades, como su nombre y descripción.
   - **Campos**:
     - `id`: Identificador único (entero o UUID).
     - `nombre`: Nombre de la actividad (texto).
     - `descripcion`: Breve descripción de la actividad (texto).

2. **Tabla `Elementos`**
   - Contiene los detalles de los elementos utilizados en las actividades (materiales, mano de obra, maquinaria, etc.).
   - **Campos**:
     - `id`: Identificador único (entero o UUID).
     - `nombre`: Nombre del elemento (texto).
     - `tipo`: Tipo de elemento (opciones como "Material", "Mano de Obra", "Maquinaria").
     - `costo`: Costo unitario del elemento (decimal).

3. **Tabla `Tabla_Vinculo`**
   - Representa la relación entre actividades y elementos, incluyendo la cantidad utilizada de cada elemento en una actividad específica.
   - **Campos**:
     - `id`: Identificador único (entero o UUID).
     - `actividad_id`: Clave foránea que referencia una actividad en la tabla `Actividades`.
     - `elemento_id`: Clave foránea que referencia un elemento en la tabla `Elementos`.
     - `cantidad`: Cantidad del elemento asociado a la actividad (decimal).

---

### **Relaciones entre las Tablas**
- **Uno a Muchos**:
  - Una actividad puede estar vinculada con varios elementos a través de la tabla `Tabla_Vinculo`.
- **Muchos a Muchos**:
  - Los elementos pueden estar asociados con múltiples actividades, lo cual se modela con la tabla `Tabla_Vinculo`.

---
