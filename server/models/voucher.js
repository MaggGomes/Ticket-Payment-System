'use strict';
module.exports = (sequelize, DataTypes) => {
	const Voucher = sequelize.define('Voucher', {
	    id: {
	      type: DataTypes.UUIDV4,
			primaryKey: true,
			allowNull: false
		},
		available: {
			type: DataTypes.BOOLEAN,
			allowNull: false,
			default: true
		},
		productId:{
	    	type: DataTypes.INTEGER,
			allowNull: true
		},
		orderId: {
			type: DataTypes.INTEGER,
			allowNull: true
		},
		userId: {
			type: DataTypes.STRING,
			allowNull: false
		}
	}, {});
	Voucher.associate = function(models) {
		Voucher.belongsTo(models.User, {
			foreignKey: 'userId'
		});
		Voucher.belongsTo(models.Order, {
			foreignKey: 'orderId'
		});
		Voucher.belongsTo(models.User, {
			foreignKey: 'productId'
		});
	};
	return Voucher;
};
