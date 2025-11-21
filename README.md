# 🏔️ THE OREGON TRAIL - Juego JavaFX

Recreación del clásico juego The Oregon Trail en JavaFX.

## 🎮 Características

- ✅ **Sin Base de Datos**: Todos los datos en memoria
- ✅ **Sin CSS**: Todo el diseño en los archivos FXML
- ✅ Viaje de 2000 millas desde Independence, Missouri hasta Oregon
- ✅ Gestión de recursos (comida, municiones, ropa, repuestos)
- ✅ Sistema de caza
- ✅ Eventos aleatorios (enfermedades, accidentes, encuentros)
- ✅ Gestión de caravana y miembros
- ✅ Diferentes profesiones con dinero inicial variable
- ✅ Sistema de clima dinámico
- ✅ Condiciones de victoria y derrota

## 📂 Estructura

```
OregonTrail/
├── src/main/
│   ├── java/
│   │   ├── module-info.java
│   │   └── com/oregontrail/
│   │       ├── Main.java
│   │       ├── controllers/      [5 controladores]
│   │       ├── models/           [4 modelos]
│   │       └── utils/            [GameManager]
│   └── resources/
│       └── com/oregontrail/
│           └── views/            [5 archivos FXML]
├── pom.xml
└── README.md
```

## 🚀 Cómo Jugar

### Instalación

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn javafx:run
```

### Controles del Juego

1. **Inicio**: Elige comenzar viaje o ver instrucciones
2. **Configuración**: Elige tu profesión y nombra a tu familia
3. **Tienda**: Compra suministros con tu dinero inicial
4. **Viaje**: Gestiona el viaje día a día
   - Continuar: Avanza un día
   - Descansar: Recupera salud
   - Cazar: Usa municiones para conseguir comida
   - Estado: Ver detalles de la caravana

## 👥 Profesiones

- **Banquero**: $1600 (Más dinero, menos resistente)
- **Carpintero**: $800 (Equilibrado)
- **Granjero**: $400 (Menos dinero, más resistente)

## 🛒 Suministros

- **Comida**: $0.20/libra - Esencial para sobrevivir
- **Municiones**: $2.00/caja - Para cazar
- **Ropa**: $10.00/conjunto - Protección
- **Ruedas**: $10.00/unidad - Repuestos para el carromato
- **Ejes**: $10.00/unidad - Repuestos
- **Lenguas**: $10.00/unidad - Repuestos

## ⚠️ Eventos Aleatorios

Durante el viaje pueden ocurrir:
- 🌩️ Tormentas que retrasan el viaje
- 👥 Encuentros con otros viajeros
- 💔 Accidentes (ruedas rotas, etc.)
- 🎁 Hallazgos de suministros
- 🦠 Enfermedades (cólera, disentería, fiebre, sarampión)

## 🏆 Condiciones de Victoria

- Recorrer las 2000 millas hasta Oregon
- Mantener vivos a los miembros de tu caravana
- Gestionar bien tus recursos

## ❌ Condiciones de Derrota

- Tu salud llega a 0
- Todos los miembros mueren
- Te quedas sin comida

## 🎯 Consejos

1. **Compra suficiente comida** (mínimo 500 libras)
2. **Caza regularmente** para ahorrar comida
3. **Descansa** si la salud está baja
4. **Compra repuestos** por si hay accidentes
5. **Gestiona el ritmo** según tus recursos

## ⚙️ Requisitos

- Java JDK 17 o superior
- Maven 3.6 o superior
- JavaFX 17.0.2 (incluido en dependencias)

## 🎨 Diseño

Todo el diseño visual está en los archivos FXML con estilos inline:
- Gradientes de colores tierra (#8B4513, #D2691E)
- Colores dorados (#FFD700) para texto destacado
- Efectos de sombra para profundidad
- Diseño temático del Oeste americano

## 📝 Notas

- Los datos se almacenan en memoria durante la partida
- No hay persistencia entre sesiones
- Los eventos son generados aleatoriamente
- El clima cambia aleatoriamente cada día

---

**¡Buena suerte en tu viaje hacia Oregon! 🏔️🛤️**

© 2024 The Oregon Trail - JavaFX Edition
