'use strict';
module.exports = (Sequelize, DataTypes) => {
	const Card = Sequelize.define('Card', {
		id:{
			type:DataTypes.UUIDV4,
			primaryKey: true,
			allowNull: false
		},
		type: {
			type: DataTypes.STRING,
			allowNull: false
		},
		number: {
			type: DataTypes.INTEGER,
			allowNull: false,
			unique: true
		},
		validity: {
			type: DataTypes.DATE,
			allowNull: false
		},
		userId: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {});
	Card.associate = function(models) {
		Card.belongsTo(models.User, {
			foreignKey: 'userId'
		});
	};
	return Card;
};
