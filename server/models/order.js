'use strict';
module.exports = (sequelize, DataTypes) => {
	const Order = sequelize.define('Order', {
		date: {
			type: DataTypes.DATE,
			allowNull: false
		},
		userId: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {});
	Order.associate = function(models) {
		Order.belongsTo(models.User, {
			foreignKey: 'userId'
		});
	};
	return Order;
};
