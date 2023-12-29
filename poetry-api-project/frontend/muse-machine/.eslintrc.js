module.exports = {
  root: true,
  env: {
    node: true,
    es2022: true
  },
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    'plugin:import/recommended'
  ],
  rules: {
    'no-unused-vars': 'off'
  },
  settings: {
    'import/resolver': {
      alias: {
        map: [
          ['@', './src']
        ],
        extensions: [".vue", ".js"]
      }
    },
  },
  overrides: [
    {
      files: [
        '**/__tests__/*.{cy,spec}.{js,ts,jsx,tsx}',
        'cypress/e2e/**/*.{cy,spec}.{js,ts,jsx,tsx}'
      ],
      'extends': [
        'plugin:cypress/recommended'
      ]
    }
  ]
}
