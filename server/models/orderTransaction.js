'use strict';
module.exports = (sequelize, DataTypes) => {
	const OrderTransaction = sequelize.define('OrderTransaction ', {
		userId : {
			type: DataTypes.STRING,
			allowNull: false
		},
		date : {
			type: DataTypes.DATE,
			allowNull: false
		},
		totalPrice: {
			type: DataTypes.DOUBLE,
			allowNull: false
		}
	}, {});
	OrderTransaction .associate = function(models) {
		OrderTransaction .belongsTo(models.User, {
			foreignKey: 'userId'
		});
	};
	return OrderTransaction;
};
