'use strict';
module.exports = (sequelize, DataTypes) => {
	const Promotion = sequelize.define('Promotion', {
		voucherId: {
			type: DataTypes.STRING,
			allowNull: false,
			primaryKey : true
		},
		productId: {
			type: DataTypes.INTEGER,
			allowNull: false,
			primaryKey : true
		},
		discount: {
			type: DataTypes.REAL,
			allowNull: false
		}
	}, {});
	Promotion.associate = function(models) {
		Promotion.belongsTo(models.Voucher, {
			foreignKey: 'voucherId'
		});
		Promotion.belongsTo(models.Product, {
			foreignKey: 'productId'
		});
	};
	return Promotion;
};
