import React from 'react';

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: 'primary' | 'secondary' | 'danger';
}

export default function Button({ variant = 'primary', ...props }: ButtonProps) {
  let base = 'p-2 rounded text-white font-medium';
  let style = '';

  switch (variant) {
    case 'primary':
      style = 'bg-blue-500 hover:bg-blue-600';
      break;
    case 'secondary':
      style = 'bg-gray-500 hover:bg-gray-600';
      break;
    case 'danger':
      style = 'bg-red-500 hover:bg-red-600';
      break;
  }

  return <button className={`${base} ${style}`} {...props} />;
}
