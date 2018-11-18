'use strict';
module.exports = (sequelize, DataTypes) => {
	const ProductOrder = sequelize.define('ProductOrder', {
		productId : {
			type: DataTypes.INTEGER,
			allowNull: false,
			primaryKey : true
		},
		orderId : {
			type: DataTypes.INTEGER,
			allowNull: false,
			primaryKey: true
		},
		quantity : {
			type: DataTypes.INTEGER,
			allowNull: false
		},
		totalPrice : {
			type: DataTypes.INTEGER,
			allowNull: false
		}

	}, {});
	ProductOrder.associate = function(models) {
		ProductOrder.belongsTo(models.Product, {
			foreignKey: 'productId'
		});
		ProductOrder.belongsTo(models.OrderTransaction, {
			foreignKey: 'orderId'
		});
	};
	return ProductOrder;
};
